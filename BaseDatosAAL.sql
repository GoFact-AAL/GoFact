CONNECT 'jdbc:derby://localhost:1527/gofactDB;create=true;user=gofact;password=gofact;';

CREATE SCHEMA GOFACT;

CREATE TABLE Usuario (
    idUsuario INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)
    , cedulaIdentidad varchar(10) NOT NULL
    , nombre varchar(30) NOT NULL
    , apellido varchar(30) NOT NULL
    , password varchar(40) NOT NULL
    , respuesta1 varchar(100) NOT NULL
    , respuesta2 varchar(100) NOT NULL
    , pregunta1 INT NOT NULL
    , pregunta2 INT NOT NULL
);

CREATE TABLE Categoria(
    idCategoria INT PRIMARY KEY NOT NULL
    , nombre varchar(20) NOT NULL
    , limite INT NOT NULL
);

CREATE TABLE Proveedor(
    idProveedor INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)
    , ruc varchar(13) NOT NULL
    , razonSocial varchar(100) NOT NULL
    , nombreComercial varchar(100) NOT NULL
    , direccion VARCHAR (100)
    , ciudad VARCHAR (30)
    , pais VARCHAR (30)
    , telefono varchar(20)
);

CREATE TABLE Factura(
    idFactura INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)
    , identificador VARCHAR (25)
    , idUsuario INT NOT NULL
    , idProveedor INT NOT NULL
    , idGasto INT NOT NULL
    , fecha DATE NOT NULL
    , totalSinIva INT NOT NULL
    , iva INT NOT NULL
    , totalTotal INT NOT NULL
    , direccion VARCHAR (100)
    , telefono VARCHAR (20)
    , CONSTRAINT factura_usuario_fk
    FOREIGN KEY (idUsuario)
    REFERENCES Usuario (idUsuario)
    , CONSTRAINT factura_proveedor_fk
    FOREIGN KEY (idProveedor)
    REFERENCES Proveedor (idProveedor)
);

CREATE TABLE Gasto(
    idGasto INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)
    , idCategoria INT NOT NULL
    , valor INT NOT NULL
    , idFactura INT NOT NULL
    , CONSTRAINT gasto_categoria_fk
    FOREIGN KEY (idCategoria)
    REFERENCES Categoria (idCategoria)
    , CONSTRAINT gasto_factura_fk
    FOREIGN KEY (idFactura)
    REFERENCES Factura (idFactura)
);

alter table Usuario add constraINT unique_cedula unique (cedulaIdentidad);
alter table Factura add constraINT unique_identificador unique (identificador);
alter table Proveedor add constraINT unique_ruc unique (ruc);

INSERT INTO Categoria (IDCATEGORIA, LIMITE, NOMBRE) VALUES (1, 10000, 'Alimentación');
INSERT INTO Categoria (IDCATEGORIA, LIMITE, NOMBRE) VALUES (2, 10000, 'Vestimenta');
INSERT INTO Categoria (IDCATEGORIA, LIMITE, NOMBRE) VALUES (3, 10000, 'Educación');
INSERT INTO Categoria (IDCATEGORIA, LIMITE, NOMBRE) VALUES (4, 10000, 'Salud');
INSERT INTO Categoria (IDCATEGORIA, LIMITE, NOMBRE) VALUES (5, 10000, 'Vivienda');
INSERT INTO Categoria (IDCATEGORIA, LIMITE, NOMBRE) VALUES (6, 10000, 'Otros');
