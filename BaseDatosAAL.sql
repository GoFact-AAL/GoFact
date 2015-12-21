
create table Usuario (
idUsuario int primary key not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
cedulaIdentidad varchar(10) not null,
nombre varchar(30) not null,
apellido varchar(30) not null,
password varchar(40) not null,
respuesta1 varchar(100) not null,
respuesta2 varchar(100) not null,
pregunta1 int not null,
pregunta2 int not null);

create table Categoria(
idCategoria int primary key not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
nombre varchar(20) not null
);

create table Gasto(
idGasto int primary key not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
idCategoria int not null,
valor int not null,
CONSTRAINT gasto_categoria_fk
	FOREIGN KEY (idCategoria)
	REFERENCES Categoria (idCategoria)
);

create table Proveedor(
idProveedor int primary key not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
ruc varchar(13) not null,
razonSocial varchar(100) not null,
nombreComercial varchar(100) not null,
direccion varchar (100),
ciudad varchar (30),
pais varchar (30),
telefono varchar(20)
);

create table Factura(
idFactura int primary key not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
idUsuario int not null,
idProveedor int not null,
idGasto int not null,
totalSinIva int not null,
iva int not null,
totalTotal int not null,
direccion varchar (100),
telefono varchar(20),
CONSTRAINT factura_usuario_fk
	FOREIGN KEY (idUsuario)
	REFERENCES Usuario (idUsuario),
CONSTRAINT factura_proveedor_fk
	FOREIGN KEY (idProveedor)
	REFERENCES Proveedor (idProveedor),
CONSTRAINT factura_gasto_fk
	FOREIGN KEY (idGasto)
	REFERENCES Gasto (idGasto)
);

alter table Usuario add constraint unique_cedula unique (cedulaIdentidad);



