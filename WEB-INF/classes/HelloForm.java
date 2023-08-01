import java.io.*;
import java.text.SimpleDateFormat;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class HelloForm extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // set content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // get details of the form and handle them accordingly
        int req_id = request.getParameter("uid");
        String docType = "<!doctype html public \" -//w3c//dtd html4.0 " +
                "transitional//en\">n\n";
        int pid = 0;
        java.sql.Date dateS = NULL;
        String date;
        try {
            switch (req_id) {
                case 2:
                    // registration
                    String name = request.getParameter("name");
                    String addr = request.getParameter("address");
                    date = request.getParameter("dob");
                    char gen;
                    if (request.getParameter("gender") == "male") {
                        gen = 'm';
                    } else {
                        gen = 'f';
                    }
                    dateS = convert(date);
                    // call the method with the parameter values
                    Patient patient = new Patient(name, addr, dateS, gen);
                    // print a page displaying the patient_id for future appointments
                    out.println(docType + "<html>\n" +
                            "<head>" +
                            "<link rel=\"stylesheet\" href=\"styles.css\">" +
                            "</head>" +
                            "<body>" +
                            "<p> your patient id is <b>" + patient.patient_id + "</b></p>" +
                            "<a href=\"Welcome.html\"><button>Back</button></a>" +
                            "</body>" +
                            "</html>");
                    break;
                // print a link to the welcome page
                case 3:
                    // cancellation
                    int aid = request.getParameter("appointment_id");
                    pid = request.getParameter("patient_id");
                    Patient.delete_appointment(pid, aid);
                    // print a confirmation( ie the appointment that was cancelled)
                    out.println(docType + "<html>\n" +
                            "<head>" +
                            "<link rel=\"stylesheet\" href=\"styles.css\">" +
                            "</head>" +
                            "<body>" +
                            "<p> thank you for letting us know</p>" +
                            "<a href=\"Welcome.html\"><button>Back</button></a>" +
                            "</body>" +
                            "</html>");
                    break;
                case 4:
                    // invoice
                    pid = request.getParameter("patient_id");
                    String invoice = Patient.get_invoice(pid);
                    out.println(docType + "<html>\n" +
                            "<head>" +
                            "<link rel=\"stylesheet\" href=\"styles.css\">" +
                            "</head>" +
                            "<body>" +
                            "<p>here is your invoice\n" +
                            invoice +
                            "</p>" +
                            "<a href=\"Welcome.html\"><button>Back</button></a>" +
                            "</body>" +
                            "</html>");
                    break;
                case 5:
                    // appointment
                    pid = request.getParameter("patient_id");
                    int sid = request.getParameter("service_id");
                    date = request.getParameter("doa");
                    dateS = convert(date);
                    int ain = Patient.make_appointment(pid, sid, dateS);
                    // call the respective method with the parameter values
                    // print the appointment number
                    out.println(docType + "<html>\n" +
                            "<head>" +
                            "<link rel=\"stylesheet\" href=\"styles.css\">" +
                            "</head>" +
                            "<body>" +
                            "<p> your appointment number is <b>" + ain + "</b></p>" +
                            "<a href=\"Welcome.html\"><button>Back</button></a>" +
                            "</body>" +
                            "</html>");
                    break;
                default:
                    throw new Exception("Invalid form submitted");

            }
        } catch (Exception e) {
            // display the error page
            out.println(docType + "<html>\n" +
                    "<head>" +
                    "<link rel=\"stylesheet\" href=\"styles.css\">" +
                    "</head>" +
                    "<body>" +
                    "<h1>" +
                    e +
                    "</h1>" +
                    "<a href=\"Welcome.html\"><button>Back</button></a>" +
                    "</body>" +
                    "</html>");
        }

    }

    private Date convert(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = sdf.parse(date);
            java.sql.Date dateS = new java.sql.Date(startDate.getTime());
            return dateS;
        } catch (Exception e) {
            throw e;
        }
    }
}
