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

        inicializarConcesionario(coches);


        Set<String> modelosUnicos = new HashSet<>();
        for (Coche coche : coches) {
            modelosUnicos.add(coche.getModelo());
        }
        List<String> modelos = new ArrayList<>(modelosUnicos);

        Set<String> coloresUnicos = new HashSet<>();
        for (Coche coche : coches) {
            coloresUnicos.add(coche.getColor());
        }
        List<String> colores = new ArrayList<>(coloresUnicos);



        app.get("/coches", ctx -> {
            Map<String, Object> model = new HashMap<>();
            model.put("modelitos", modelos);
            model.put("colorcitos", colores);
            model.put("coches", coches);
            ctx.render("coches/coches.ftl", model);
        });

        app.post("/coches", ctx -> {
            Map<String, Object> model = new HashMap<>();

            String modeloInput = ctx.formParam("modeloInput");
            System.out.println(modeloInput);

            String colorInput = ctx.formParam("colorInput");
            System.out.println(colorInput);

            int precioInput = 0;

            
            precioInput = Integer.parseInt(ctx.formParam("precioInput"));
            System.out.println(precioInput);





            List<Coche> cochesFiltrados = new ArrayList<>(coches);

            filtrarModeloCoches(modeloInput, cochesFiltrados);
            filtrarColorCoches(colorInput, cochesFiltrados);
            filtrarPrecioCoches(precioInput, cochesFiltrados);

            model.put("modelitos", modelos);
            model.put("colorcitos", colores);
            model.put("modeloSeleccionado", modeloInput);
            model.put("colorSeleccionado", colorInput);
            model.put("coches", cochesFiltrados);
            ctx.render("coches/coches.ftl", model);
        });



    }

    private static void filtrarPrecioCoches(int precioInput, List<Coche> coches) {
        if(precioInput<=0){
            return;
        }



        Iterator<Coche> iterador = coches.iterator();
        while(iterador.hasNext()){
            Coche coche = iterador.next();
            if(coche.getPrecio() > precioInput){
                iterador.remove();
            }
        }

    }

    private static void filtrarColorCoches(String colorInput, List<Coche> coches) {

        if(colorInput==null || colorInput.isEmpty()){
            return;
        }

        Iterator<Coche> iterador = coches.iterator();
        while(iterador.hasNext()){
            Coche coche = iterador.next();
            if(!coche.getColor().equals(colorInput)){
                iterador.remove();
            }
        }

    }

    private static void inicializarConcesionario(List<Coche> coches) {

        coches.add(new Coche("/img/coche1.jpg", 20000, "Ford", "Rojo"));
        coches.add(new Coche("/img/coche2.jpg", 25500, "Honda", "Negro"));
        coches.add(new Coche("/img/coche3.jpg", 30000, "Ford", "Azul"));
        coches.add(new Coche("/img/coche4.jpg", 18000, "Renault", "Blanco"));
        coches.add(new Coche("/img/coche5.jpg", 22750, "Ford", "Gris"));

    }

    private static void filtrarModeloCoches(String modeloInput, List<Coche> coches) {

        if(modeloInput==null || modeloInput.isEmpty()){
            return;
        }

        Iterator<Coche> iterador = coches.iterator();
        while(iterador.hasNext()){
            Coche coche = iterador.next();
            if(!coche.getModelo().equals(modeloInput)){
                iterador.remove();
            }
        }


    }

}
