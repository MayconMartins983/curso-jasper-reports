package com.mballem.curso.jasper.spring.service;

import com.mballem.curso.jasper.spring.entity.Funcionario;
import com.mballem.curso.jasper.spring.entity.Produto;
import com.mballem.curso.jasper.spring.enums.EVizualizar;
import com.mballem.curso.jasper.spring.repository.FuncionarioRepository;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.Connection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class JasperService {

    private static final String JASPER_DIRECTORY = "classpath:jasper/";
    private static final String JASPER_DIRECTORY_IMAGES = "classpath:jasper/";
    private static final String JASPER_PREFIXO = "funcionarios-";
    private static final String JASPER_SUFIXO = ".jasper";

    @Autowired
    private Connection connection;

    @Autowired
    private FuncionarioRepository repository;

    private Map<String, Object> params = new HashMap<>();

    public void addParams(String key, Object value) {
        this.params.put(key, value);
    }

    public void exportarPdfWithHashMap(String code, HttpServletResponse response, EVizualizar vizualizar) {
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);

        if (vizualizar == EVizualizar.BAIXAR) {
            response.setHeader("content-disposition", "inline; filename=relatorio-" + code + ".pdf");
        } else if (vizualizar == EVizualizar.VIZUALIZAR) {
            response.setHeader("content-disposition", "attachment; filename=relatorio-" + code + ".pdf");
        }
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("nome", "Maycon");
        hashMap.put("idade", "25");
        hashMap.put("cidade", "Brasília");


        try {
            File file = ResourceUtils.getFile(JASPER_DIRECTORY.concat(code).concat(JASPER_SUFIXO));
            JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(Collections.singletonList(hashMap));
            JasperPrint jasperPrint = JasperFillManager.fillReport(file.getAbsolutePath(), params, dataSource);
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void exportarPdf(String code, HttpServletResponse response, EVizualizar vizualizar) {
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);

        if (vizualizar == EVizualizar.BAIXAR) {
            response.setHeader("content-disposition", "inline; filename=relatorio-" + code + ".pdf");
        } else if (vizualizar == EVizualizar.VIZUALIZAR) {
            response.setHeader("content-disposition", "attachment; filename=relatorio-" + code + ".pdf");
        }

        try {
            File file = ResourceUtils.getFile(JASPER_DIRECTORY.concat(code).concat(JASPER_SUFIXO));
            JasperPrint jasperPrint = JasperFillManager.fillReport(file.getAbsolutePath(), params, connection);
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void exportarPdf02(String code, HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("content-disposition", "inline; filename=relatorio-" + code + ".pdf");

        try {
            File file = ResourceUtils.getFile(JASPER_DIRECTORY.concat(code).concat(JASPER_SUFIXO));
            JasperPrint jasperPrint = JasperFillManager.fillReport(file.getAbsolutePath(), params, connection);
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void exportarPdfTeste(String code, HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("content-disposition", "inline; filename=relatorio-" + code + ".pdf");
        var funcionario = repository.findById(1L);

        try {
            this.addParams("NOME", funcionario.get().getNome());
            this.addParams("SALARIO", funcionario.get().getSalario());
            this.addParams("IMAGEM_DIRETORIO", JASPER_DIRECTORY_IMAGES);
            this.addParams("PRODUTO", new Produto("Carro", "Verde"));
            File file = ResourceUtils.getFile(JASPER_DIRECTORY.concat(code).concat(JASPER_SUFIXO));
            JasperPrint jasperPrint = JasperFillManager.fillReport(file.getAbsolutePath(), params, connection);
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
