package ru.sberbank.edu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Hello world!
 *
 */


public class FinancialServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setContentType("text/html;charset=UTF-8");
       String filePath = getServletContext().getRealPath("/get.jsp");
        File file = new File(filePath);
        Writer writer=resp.getWriter();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null) {
            writer.write(line);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setContentType("text/html;charset=UTF-8");
        Writer writer=resp.getWriter();
        try{

        int sum=Integer.parseInt(req.getParameter("sum"));
        double percentage=Double.parseDouble(req.getParameter("percentage"));
        int years=Integer.parseInt(req.getParameter("years"));
        if(sum<50_000){
            writer.write("<html lang=\"ru\">");
            writer.write("<head><meta charset=\"UTF-8\" /></head>");
            writer.write("<body>");
            writer.write("<h3>ОШИБКА</h3>");
            writer.write("<p>Минимальная сумма на момент открытия вклада 50 000 рублей</p>");
            writer.write("</body>");
            writer.write("</html>");
        } else if (sum<0 || percentage<0 || years<0) {
            writer.write("<html lang=\"ru\">");
            writer.write("<head><meta charset=\"UTF-8\" /></head>");
            writer.write("<body>");
            writer.write("<h3>ОШИБКА</h3>");
            writer.write("<p>Неверный формат данных. Скорректируйте значения</p>");
            writer.write("</body>");
            writer.write("</html>");
        } else {
            double r = percentage / 100;
            int n = 1;
            String futureValue = String.valueOf((int) (sum * Math.pow(1 + r / n, n * years)));
            writer.write("<html lang=\"ru\">");
            writer.write("<head><meta charset=\"UTF-8\" /></head>");
            writer.write("<body>");
            writer.write("<h3>Результат</h3>");
            writer.write("<p>Итоговая сумма ");
            writer.write(futureValue);
            writer.write(" рублей</p>");
            writer.write("</body>");
            writer.write("</html>");
        }} catch(NumberFormatException e){
            writer.write("<html lang=\"ru\">");
            writer.write("<head><meta charset=\"UTF-8\" /></head>");
            writer.write("<body>");
            writer.write("<h3>Результат</h3>");
            writer.write("<p>Неверный формат данных. Скорректируйте значения</p>");
            writer.write("</body>");
            writer.write("</html>");

        }
    }
}
