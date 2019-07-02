CREATE TABLE `garita`
(
  `garita_id` int UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255),
  `poblacion` int,
  `description` varchar(255),
  `nombre_web` varchar(255)
);

CREATE TABLE `rol`
(
  `idRol` int UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255),
  `descripcion` varchar(255)
);

CREATE TABLE `usuario`
(
  `idUsuario` int UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nombres` varchar(255),
  `apePaterno` varchar(255),
  `apeMaterno` varchar(255),
  `fecNacimiento` date,
  `email` varchar(255),
  `activo` boolean,
  `idRol` int,
  `usuario` varchar(255),
  `password` varchar(255),
  `fecRegistro` date
);

CREATE TABLE `vivienda`
(
  `idVivienda` int UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255),
  `nombreContacto` varchar(255),
  `telefonoContacto` varchar(255),
  `activo` boolean,
  `idRol` int,
  `email` varchar(255),
  `usuario` varchar(255),
  `password` varchar(255),
  `fecRegistro` date
);

CREATE TABLE `residente`
(
  `idResidente` int UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `idVivienda` int,
  `nombres` varchar(255),
  `apeMaterno` varchar(255),
  `apePaterno` varchar(255),
  `fecNacimiento` date,
  `genero` varchar(255),
  `email` varchar(255),
  `activo` boolean,
  `fecRegistro` date
);

CREATE TABLE `censo`
(
  `idCenso` int UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `fechaRegistro` date,
  `fechaInicio` date,
  `periodo` int,
  `estado` varchar(255),
  `viviendas` int,
  `vivEncuestadas` int
);

CREATE TABLE `formulario`
(
  `idFormulario` int UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255),
  `tipo` varchar(255),
  `estado` varchar(255)
);

CREATE TABLE `formulariosCenso`
(
  `idCenso` int NOT NULL,
  `idFormulario` int NOT NULL,
  `activo` boolean NOT NULL
);

CREATE TABLE `pregunta`
(
  `idPregunta` int UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `TipoPregunta` varchar(255) NOT NULL,
  `pregunta` varchar(255) NOT NULL,
  `clave` varchar(255) NOT NULL
);

CREATE TABLE `preguntaFormulario`
(
  `idFormulario` int NOT NULL,
  `idPregunta` int NOT NULL,
  `activo` boolean NOT NULL
);

CREATE TABLE `opcion`
(
  `idOpcion` int UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(255)
);

CREATE TABLE `opcionPregunta`
(
  `idPregunta` int NOT NULL,
  `idOpcion` int NOT NULL,
  `activo` boolean NOT NULL
);

CREATE TABLE `respuesta`
(
  `idRespuesta` int UNIQUE PRIMARY KEY NOT NULL,
  `idCenso` int,
  `idUsuario` int,
  `idVivienda` int,
  `idResidente` int,
  `idFormulario` int,
  `idPregunta` int,
  `idOpcion` int,
  `clave` varchar(255),
  `valrespuesta` varchar(255)
);

CREATE TABLE `censoResultado`
(
  `idCensoResultado` int UNIQUE PRIMARY KEY NOT NULL,
  `idRespuesta` int NOT NULL
);

CREATE TABLE `censoVivienda`
(
  `idCenso` int NOT NULL,
  `idUsuario` int NOT NULL,
  `idVivienda` int NOT NULL,
  `idCensoResultado` int NOT NULL,
  `estado` varchar(255)
);

CREATE TABLE `censoResidente`
(
  `idCenso` int NOT NULL,
  `idUsuario` int NOT NULL,
  `idVivienda` int NOT NULL,
  `idResidente` int NOT NULL,
  `idCensoResultado` int NOT NULL,
  `estado` varchar(255)
);

ALTER TABLE `usuario` ADD FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`);

ALTER TABLE `vivienda` ADD FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`);

ALTER TABLE `residente` ADD FOREIGN KEY (`idVivienda`) REFERENCES `vivienda` (`idVivienda`);

ALTER TABLE `formulariosCenso` ADD FOREIGN KEY (`idCenso`) REFERENCES `censo` (`idCenso`);

ALTER TABLE `formulariosCenso` ADD FOREIGN KEY (`idFormulario`) REFERENCES `formulario` (`idFormulario`);

ALTER TABLE `preguntaFormulario` ADD FOREIGN KEY (`idFormulario`) REFERENCES `formulario` (`idFormulario`);

ALTER TABLE `preguntaFormulario` ADD FOREIGN KEY (`idPregunta`) REFERENCES `pregunta` (`idPregunta`);

ALTER TABLE `opcionPregunta` ADD FOREIGN KEY (`idPregunta`) REFERENCES `pregunta` (`idPregunta`);

ALTER TABLE `opcionPregunta` ADD FOREIGN KEY (`idOpcion`) REFERENCES `opcion` (`idOpcion`);

ALTER TABLE `censoVivienda` ADD FOREIGN KEY (`idCenso`) REFERENCES `censo` (`idCenso`);

ALTER TABLE `censoResidente` ADD FOREIGN KEY (`idCenso`) REFERENCES `censo` (`idCenso`);

ALTER TABLE `censoVivienda` ADD FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`);

ALTER TABLE `censoResidente` ADD FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`);

ALTER TABLE `censoVivienda` ADD FOREIGN KEY (`idVivienda`) REFERENCES `vivienda` (`idVivienda`);

ALTER TABLE `censoResidente` ADD FOREIGN KEY (`idVivienda`) REFERENCES `vivienda` (`idVivienda`);

ALTER TABLE `censoResidente` ADD FOREIGN KEY (`idResidente`) REFERENCES `residente` (`idResidente`);


insert into rol(`nombre`, `descripcion`) values ('admin', 'administrador');


insert into usuario(`nombres`, `idRol`, `usuario`, `password`, `fecRegistro`)
values ('Gonchi Campos', 1, 'admin', 'Server1', curdate());
