package com.example.bistro_springboot.controller;

import com.example.bistro_springboot.model.Employee;
import com.example.bistro_springboot.service.EmployeeService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Employee> list = employeeService.getAllEmployee();
        model.addAttribute("list", list);
        return "index";
    }

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file")MultipartFile file, Model model) throws IOException {
        Employee employee = new Employee();
        String fileName = file.getOriginalFilename();
        employee.setProfilePicture(fileName);
        employee.setContent(file.getBytes());
        employee.setSize(file.getSize());
        employeeService.createEmployee(employee);
        model.addAttribute("success", "File uploaded Successfully!!");
        return "index";
    }

    @GetMapping("/downloadfile")
    public void downloadFile(@Param("id") Long id, Model model, HttpServletResponse response) throws IOException {
        Optional<Employee> temp = employeeService.findEmployeeById(id);
        if (temp!= null) {
            Employee employee = temp.get();
            response.setContentType("application/octet-steam");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename = " +employee.getProfilePicture();
            response.setHeader(headerKey,headerValue);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(employee.getContent());
            outputStream.close();
        }
    }

    @GetMapping("/image")
    public void showImage(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {
        Optional<Employee> employeeOptional = employeeService.findEmployeeById(id);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            // Set the content type based on the image type
            response.setContentType("image/jpeg" + " image/jpg");

            try (OutputStream outputStream = response.getOutputStream()) {
                outputStream.write(employee.getContent());
            }
        } else {
            // Handle the case when the employee with the specified ID is not found
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
