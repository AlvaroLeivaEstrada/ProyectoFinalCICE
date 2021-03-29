

import random

def productoGenerador(file):
	for x in range(1,100):
		
		producto=random.randint(1,100)
		nombre="'PRODUCTO_"+str(producto)+"'"
		precio = random.randint(1,100)
		line='insert into producto (nombre, precio_unitario) VALUES({},{});\n'.format(nombre,precio)
		file.write(line)

def ventasGenerador(file):
	for venta in range(1,1000):
		almacen=random.randint(1,10)
		producto=random.randint(1,100)
		year= random.randint(2015,2020)
		line='insert into ventas(almacen_id,ano_venta,producto_id) values ({},{},{});\n'.format(almacen,year,producto)
		file.write(line)
	

if __name__ == '__main__':
	#venta = open("ventas.txt", 'w') 
	producto = open("productos.txt",'w')
	#ventasGenerador(venta);
	productoGenerador(producto)
	#venta.close();
	producto.close();