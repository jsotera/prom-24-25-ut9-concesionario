package es.masanz.ut9.pruebas.concesionario;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.rendering.template.JavalinFreemarker;

import java.util.*;

public class MainCoches {

    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/static");
            config.fileRenderer(new JavalinFreemarker());
        }).start(7000);

        List<Coche> coches = new ArrayList<>();
        coches.add(new Coche("/img/coche1.jpg", "20,000€", "Ford", "Rojo"));
        coches.add(new Coche("/img/coche2.jpg", "25,500€", "Honda", "Negro"));
        coches.add(new Coche("/img/coche3.jpg", "30,000€", "Ford", "Azul"));
        coches.add(new Coche("/img/coche4.jpg", "18,000€", "Renault", "Blanco"));
        coches.add(new Coche("/img/coche5.jpg", "22,750€", "Ford", "Gris"));

        Set<String> modelosUnicos = new HashSet<>();
        for (Coche coche : coches) {
            modelosUnicos.add(coche.getModelo());
        }

        List<String> modelos = new ArrayList<>(modelosUnicos);



        app.get("/coches", ctx -> {
            Map<String, Object> model = new HashMap<>();
            model.put("modelitos", modelos);
            model.put("coches", coches);
            ctx.render("coches/coches.ftl", model);
        });

        app.post("/coches", ctx -> {
            Map<String, Object> model = new HashMap<>();

            String modeloInput = ctx.formParam("modeloInput");
            System.out.println(modeloInput);

            model.put("modelitos", modelos);
            model.put("modeloSeleccionado", modeloInput);
            model.put("coches", coches);
            ctx.render("coches/coches.ftl", model);
        });

    }

}
