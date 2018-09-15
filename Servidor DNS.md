### Actualiza los repositorios
```
apt-get update
```
### Instala el servidor DNS
```
apt-get install bind9 
```
### Instala las herramientas de validacion para DNS
```
apt-get install dnsutils 
```
### Para ver el nombre de la maquina
```
hostname
```

-----------------------------
SERVIDOR DNS MAESTRO PRIMARIO
-----------------------------

## named.conf.local

	cd /etc/bind
	
	nano named.conf.local

#### //Zona directa
```
zone "proyecto2.com"{
	type master;
	file "etc/bind/db.[dominio]" (1)
};
```
#### //Zona inversa
```
zone "0.168.192.in-addr.arpa"{
	type master;
	file "etc/bind/db.192"; (2)
};
```
## (1)
```
cp db.empty db.[dominio]
nano db.[dominio]

@	IN	SOA	[dominio].	root.[dominio].

.
.
.
@	IN	NS	[Nombre de la Máquina]
[Nombre]IN	A	[IP Maquina]
www		CNAME	[Nombre de la Máquina]
proyecto2.com.	A	[IP Maquina]
```
## (2)
```
cp db.127 db.192
nano db.192

@	IN	SOA	[dominio].	root.[dominio].

.
.
.
@	IN	NS	[Nombre de la Máquina]
[Porcion6
Host]	IN	PTR	[Nombre de la Máquina]
[Nombre
Maquina]	A	[IP Maquina]
```

## /etc/resolv.conf
```
domain proyecto2.com
search proyecto2.com
nameserver 192.168.0.50
```

-------------------------------
SERVIDOR DNS MAESTRO SECUNDARIO
-------------------------------

## MAQUINA DNS SECUNDARIO

Añadir en /etc/bind/named.conf.local del DNS secundario
```
zone "dominio" {
type slave;
file "/etc/bind/ieslapaloma.db";
masters { 192.168.0.50; };
};

zone "0.168.192.in-addr.arpa" {
type slave;
file "/etc/bind/192.rev";
masters { 192.168.0.50; };
}; 
```
## MAQUINA DNS PRIMARIO

Añadir linea en /etc/bind/db.[dominio] del DNS primario

	IN	dns2.[dominio]

Añadir linea en /etc/bind/db.192 del DNS primario

	IN	dns2.[dominio]

### Archivo /etc/bind/named.conf.local del maestro
```
zone "ieslapaloma.com" {
type master;
file "/etc/bind/db.[dominio]";
also-notify {ip_del_esclavo;}
};

zone "0.168.192.in-addr.arpa" {
type master;
file "/etc/bind/db.192";
also-notify {ip_del_esclavo;}
};
```