DROP DATABASE IF EXISTS poo2_nomina;
CREATE DATABASE poo2_nomina;

USE poo2_nomina;

CREATE TABLE carrera (
	codigo varchar(3) NOT NULL,
	nombre varchar(50) NOT NULL,
	semestres int(2) NOT NULL,
	facultad varchar(1) NOT NULL,
	PRIMARY KEY (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE asignatura (
	cod_asignatura varchar(4) NOT NULL,
	cod_carrera varchar(3) NOT NULL,
	nombre varchar(50) NOT NULL,
	creditos int(2) NOT NULL,
	PRIMARY KEY (cod_asignatura),
	FOREIGN KEY (cod_carrera) REFERENCES carrera(codigo) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE requisito (
	cod_requisito varchar(4), 
	cod_asignatura varchar(4), 
	cod_carrera varchar(3),
	PRIMARY KEY (cod_requisito),
	FOREIGN KEY (cod_asignatura) REFERENCES asignatura(cod_asignatura) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (cod_carrera) REFERENCES carrera(codigo) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE alumno (
	cod_alumno varchar(4),
	cod_carrera varchar(3),
	nombre varchar(50),
	apellido varchar(50),
	direccion varchar(50),
	telefono varchar(50),
	email varchar(50),
	PRIMARY KEY (cod_alumno),
	FOREIGN KEY (cod_carrera) REFERENCES carrera(codigo) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE tutor (
	cod_tutor varchar(4),
	cod_alumno varchar(4),
	cod_carrera varchar(3),
	PRIMARY KEY (cod_tutor),
	FOREIGN KEY (cod_alumno) REFERENCES alumno(cod_alumno) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (cod_carrera) REFERENCES carrera(codigo) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE nota (
	cod_asignatura varchar(4),
	cod_carrera varchar(3),
	cod_alumno varchar(4),
	nota decimal(2, 1),
	FOREIGN KEY (cod_asignatura) REFERENCES asignatura(cod_asignatura) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (cod_alumno) REFERENCES alumno(cod_alumno) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (cod_carrera) REFERENCES carrera(codigo) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE equivalente (
	cod_asignatura varchar(4),
	cod_carrera varchar(3),
	cod_alumno varchar(4),
	cod_carrera_mat varchar(3),
	cod_asignatura_mat varchar(4),
	FOREIGN KEY (cod_asignatura) REFERENCES asignatura(cod_asignatura) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (cod_alumno) REFERENCES alumno(cod_alumno) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (cod_carrera) REFERENCES carrera(codigo) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (cod_carrera_mat) REFERENCES carrera(codigo) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (cod_asignatura_mat) REFERENCES asignatura(cod_asignatura) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

