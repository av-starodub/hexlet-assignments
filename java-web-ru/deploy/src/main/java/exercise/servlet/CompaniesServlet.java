package exercise.servlet;

import exercise.Data;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Objects;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // BEGIN
        PrintWriter out = response.getWriter();
        var companies = Data.getCompanies();
        var searchKey = request.getParameter("search");

        if (Objects.isNull(searchKey) || searchKey.isEmpty()) {
            companies.forEach(out::println);
            return;
        }
        var foundCompanies = companies.stream()
                .filter(comp -> comp.contains(searchKey))
                .toList();
        if (foundCompanies.isEmpty()) {
            out.println("Companies not found");
            return;
        }
        foundCompanies.forEach(out::println);
        // END
    }
}
