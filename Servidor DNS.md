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
SERVIDOR DNS MAESTRO
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
domain [tudominio]
search [tudominio]
nameserver [IP del Servidor DNS]
```

-------------------------------
SERVIDOR DNS ESCLAVO
-------------------------------

## MAQUINA DNS ESCLAvo

Añadir en /etc/bind/named.conf.local del DNS esclavo
```
zone "dominio" {
type slave;
file "/etc/bind/db.[dominio].2";
masters { [IP del servidor DNS maestro]; };
};

zone "0.168.192.in-addr.arpa" {
type slave;
file "/etc/bind/db.192.2";
masters { [IP del servidor DNS maestro]; };
}; 
```
## MAQUINA DNS MAESTRO

Añadir linea en /etc/bind/db.[dominio] del DNS maestro

	IN	dns2.[dominio]

Añadir linea en /etc/bind/db.192 del DNS maestro

	IN	dns2.[dominio]

### Archivo /etc/bind/named.conf.local del maestro
```
zone "[tudominio].com" {
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