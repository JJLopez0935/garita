CREATE TABLE "garita" (
  "garita_id" int UNIQUE PRIMARY KEY NOT NULL,
  "nombre" varchar,
  "poblacion" int,
  "description" varchar,
  "nombre_web" varchar
);

CREATE TABLE "rol" (
  "rol_id" int UNIQUE PRIMARY KEY NOT NULL,
  "nombre" varchar,
  "description" varchar
);

CREATE TABLE "usuario" (
  "usuario_id" int UNIQUE PRIMARY KEY NOT NULL,
  "persona_id" int,
  "nombres" varchar,
  "ap_pat" varchar,
  "ap_mat" varchar,
  "rol_id" int,
  "email" varchar,
  "fecnacimiento" date,
  "password" varchar
);

CREATE TABLE "integrante" (
  "integrante_id" int UNIQUE PRIMARY KEY NOT NULL,
  "familia_id" int,
  "nombres" varchar,
  "ap_pat" varchar,
  "ap_mat" varchar,
  "fecnacimiento" date,
  "educacion" varchar
);

CREATE TABLE "familia" (
  "familia_id" int UNIQUE PRIMARY KEY NOT NULL,
  "direccion" varchar,
  "telefono" varchar,
  "integrantes" int,
  "perros" int,
  "gatos" int,
  "otras_animales" int,
  "m_terreno" int,
  "m_construido" int,
  "m_jarin" int,
  "agua_compradalt_sem" int,
  "agua_consumolt_sem" int,
  "agua_recicladalt_sem" int,
  "activo" int
);

CREATE TABLE "censo" (
  "censo_id" int UNIQUE PRIMARY KEY NOT NULL,
  "fecha" date
);

CREATE TABLE "censo_familia" (
  "id" int UNIQUE PRIMARY KEY NOT NULL,
  "censo_id" int NOT NULL,
  "familia_id" int NOT NULL,
  "usuario_id" int NOT NULL,
  "fecha" date
);

CREATE TABLE "reporte_solucion" (
  "reporte_id" int UNIQUE PRIMARY KEY NOT NULL,
  "reporte_campos" varchar,
  "reporte_filtros" varchar
);

ALTER TABLE "rol" ADD FOREIGN KEY ("rol_id") REFERENCES "usuario" ("rol_id");

ALTER TABLE "familia" ADD FOREIGN KEY ("familia_id") REFERENCES "usuario" ("usuario_id");

ALTER TABLE "integrante" ADD FOREIGN KEY ("familia_id") REFERENCES "familia" ("familia_id");

ALTER TABLE "censo_familia" ADD FOREIGN KEY ("censo_id") REFERENCES "censo" ("censo_id");

ALTER TABLE "censo_familia" ADD FOREIGN KEY ("usuario_id") REFERENCES "usuario" ("usuario_id");

ALTER TABLE "familia" ADD FOREIGN KEY ("familia_id") REFERENCES "censo_familia" ("familia_id");