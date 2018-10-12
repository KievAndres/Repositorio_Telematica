var PORT = 33333;
var HOST = '127.0.0.1';



var dgram = require('dgram');
var server = dgram.createSocket('udp4');

server.on('listening', function () {
    var address = server.address();
    console.log('UDP Server listening on ' + address.address + ":" + address.port);
});

server.on('message', function (message, remote) {
    console.log(remote.address + ':' + remote.port +' - ' + message);
    let puertos = [
        {
            puerto: 1,
            prefijoc: '1100',
            coincidencias: 0
        },
        {
            puerto: 2,
            prefijoc: '1101',
            coincidencias: 0
        },
        {
            puerto: 3,
            prefijoc: '1110',
            coincidencias: 0
        }
    ]
    let max=0;
    let puertomax=0;
    let mensaje = String(message);
    for(let i=0; i<mensaje.length; i++){
        for(let j=0; j<puertos.length; j++){
            if(j<puertos[j].prefijoc.length){
                if(mensaje.charAt(i) == puertos[j].prefijoc.charAt(i)){
                    puertos[j].coincidencias++;
                    if(puertos[j].coincidencias > max){
                        max=puertos[j].coincidencias;
                        puertomax=puertos[j].puerto;
                    }
                }
            }
        }
    }
    console.log(puertos);
    console.log('Se va por el puerto: '+puertomax);

});


server.bind(PORT, HOST);