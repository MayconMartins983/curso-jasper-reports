package com.mballem.curso.jasper.spring.controller;

import com.mballem.curso.jasper.spring.enums.EVizualizar;
import com.mballem.curso.jasper.spring.service.JasperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("relatorio")
public class JasperController {

    @Autowired
    private JasperService service;

    @GetMapping
    public void gerarRelatorio01(@RequestParam String code, @RequestParam EVizualizar vizualizar,
                                 HttpServletResponse response) {
        service.exportarPdf(code, response, vizualizar);

    }

    @GetMapping("hash-map")
    public void gerarRelatorioHashMap(@RequestParam String code, @RequestParam EVizualizar vizualizar,
                                 HttpServletResponse response) {
        service.exportarPdfWithHashMap(code, response, vizualizar);

    }

    @GetMapping("01/{code}")
    public void gerarRelatorio09(@PathVariable String code, @RequestParam String nivel, @RequestParam String uf,
                                 HttpServletResponse response) {
        service.addParams("NIVEL_DESC", nivel.isBlank() ? null : nivel);
        service.addParams("UF", uf.isBlank() ? null : uf);
        service.exportarPdf02(code, response);

    }

    @GetMapping("teste")
    public void gerarRelatorioTeste(@RequestParam String code,
                                    HttpServletResponse response) {
        service.exportarPdfTeste(code, response);
    }
}
