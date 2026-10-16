package com.a00.laboratorio.comun.notasv1;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.header;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;


public class NotasSimulation extends Simulation {

    HttpProtocolBuilder protocolo = http
            .baseUrl(System.getProperty("baseUrl", "http://localhost:8080"))
            .contentTypeHeader("application/json");

    ScenarioBuilder crearYLeer = scenario("Crear y leer una nota")
            .exec(http("crear").post("/api/v1/notes")
                    .body(StringBody("""
                            {"title": "carga", "body": "#{randomUuid()}"}
                            """))
                    .check(status().is(201), header("Location").saveAs("location")))
            .exec(http("leer").get("#{location}")
                    .check(status().is(200)));

    {
        setUp(crearYLeer.injectOpen(constantUsersPerSec(50).during(60)))
                .protocols(protocolo)
                .assertions(global().responseTime().percentile(99.0).lt(250));
    }
}