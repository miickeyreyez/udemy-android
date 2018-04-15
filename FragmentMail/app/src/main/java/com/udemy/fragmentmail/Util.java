package com.udemy.fragmentmail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by INSPIRON on 15/4/2018.
 */

public class Util {

    public static String[] colors = new String[]{
            "#cd84f1","#ffb8b8","#ff3838","#fff200","#7d5fff",
            "#7158e2","#18dcff","#17c0eb","#67e6dc","#7efff5",
            "#32ff7e","#218c74","#C4E538","#ED4C67","#6F1E51"
    };

    public static List<Mail> getDummyData(){
        return new ArrayList<Mail>(){{
            add(new Mail("Cotización","Hola me podrías ayudar con el plan de cotización del modelo 2018","Rubén Rodríguez"));
            add(new Mail("Presupuesto","La liberación del presupuesto será la siguiente semana","Roberto Hernández"));
            add(new Mail("Estatus Venta","Hola, me comunico para saber cuando será la entrega de mi nuevo vehículo, saludos!","Andrés Gómez"));
            add(new Mail("Modelo 2019","La presentación del modelo 2019, será en Santa Fe, está usted invitado","Corporativo"));
            add(new Mail("Cotización","Hola me podrías ayudar con el plan de cotización del modelo 2018","Rubén Rodríguez"));
            add(new Mail("Presupuesto","La liberación del presupuesto será la siguiente semana","Roberto Hernández"));
            add(new Mail("Estatus Venta","Hola, me comunico para saber cuando será la entrega de mi nuevo vehículo, saludos!","Andrés Gómez"));
            add(new Mail("Modelo 2019","La presentación del modelo 2019, será en Santa Fe, está usted invitado","Corporativo"));
            add(new Mail("Cotización","Hola me podrías ayudar con el plan de cotización del modelo 2018","Rubén Rodríguez"));
            add(new Mail("Presupuesto","La liberación del presupuesto será la siguiente semana","Roberto Hernández"));
            add(new Mail("Estatus Venta","Hola, me comunico para saber cuando será la entrega de mi nuevo vehículo, saludos!","Andrés Gómez"));
            add(new Mail("Modelo 2019","La presentación del modelo 2019, será en Santa Fe, está usted invitado","Corporativo"));
        }};
    }

    public static String getRandomColor(){
        //Número aleatorio entre 0 y 14
        int randomNumber = new Random().nextInt(colors.length) + 0;
        System.out.print("Color:" + colors[randomNumber]);
        return colors[randomNumber];
    }
}