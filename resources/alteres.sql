select * from proveedores;
select * from clientes;
select * from productos;

select * from sector;


alter table fraccionamieno rename to fraccionamiento;


INSERT INTO fraccionamiento(id, codigo, descripcion) VALUES (1, 'u' , 'UNIDAD');
INSERT INTO fraccionamiento(id, codigo, descripcion) VALUES (2, 'ml' , 'MILILITRO');
INSERT INTO fraccionamiento(id, codigo, descripcion) VALUES (3, 'g' , 'GRAMO');

INSERT INTO sector(id, codigo, descripcion, id_fraccionamiento) VALUES (1,'A','Solidos',3);
INSERT INTO sector(id, codigo, descripcion, id_fraccionamiento) VALUES (2,'B','Liquidos',2);
INSERT INTO sector(id, codigo, descripcion, id_fraccionamiento) VALUES (3,'C','Embalaje',1);

INSERT INTO productos(id, codigo, descripcion, stock_minimo, cantidad_fraccionamiento, 
            factor_ganancia, id_sector)
    VALUES (nextVal('productos_id_seq'), 'GLU', 'Glucosa', 300, 100, 
            30, 1);

INSERT INTO proveedores(id, razon_social, ruc, direccion, telefono, telefono_movil, email,pais, contacto)
    VALUES (nextVal('proveedores_id_seq'), 'Jorge Raul García Ayala', '4489768-5', 'N/A', '1234565', '123456789', 'algo@mail.com','PY','conmigo' );
