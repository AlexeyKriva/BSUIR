package com.editor.segmenteditor.controllers;

import com.editor.segmenteditor.models.Algorithm;
import com.editor.segmenteditor.models.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@Controller
@SessionAttributes("segment")
public class segmentController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("segment", new Segment());
        return "home";
    }

    @PostMapping("/create")
    public String createSegment(@Valid @ModelAttribute("segment") Segment segment,
                                BindingResult bindingResult){

        System.out.println(segment);
        if (bindingResult.hasErrors()) {
            return "home";
        }


        switch (segment.getAlgorithm()) {
            case DDA -> System.out.println("DDA");
            case Bresenham -> System.out.println("Bresenham");
            case Vu -> System.out.println("Vu");
        }

        return "redirect: /create";
    }

    @GetMapping("/getImage")
    @ResponseBody
    public byte[] getImage() throws IOException {
        BufferedImage image = drawLineOnImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }

    private BufferedImage drawLineOnImage() {
         BufferedImage image = new BufferedImage(333, 333, BufferedImage.TYPE_INT_RGB);
         Graphics2D g2d = image.createGraphics();
         g2d.drawLine(1, 1, 100, 100);
         return image;
    }

    @GetMapping("/drawLine")
    public String drawLine() {
        drawLineOnImage();
        return "drawLine";
    }
}