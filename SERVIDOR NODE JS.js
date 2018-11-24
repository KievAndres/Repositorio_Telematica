//Solicita una librería para crear un Servidor HTTP
var servidor = require('http');
//Uso de la Función anónima, función flecha () => {}
servidor.createServer((peticion, respuesta) => {
    //MIME-TYPE de tipo texto con extensión HTML
    respuesta.writeHead(200, {"Content-Type": "text/html"});
    //escritura en la página html
    respuesta.write('Telematica, INF - 273');
    //Envío de respuesta al cliente
    respuesta.end();
    //¿En que puerto se ejecutará el servidor?
}).listen(1234);