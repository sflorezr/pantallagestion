# language: es
# Autor: sflorez@solati.com.co

  Característica: Pruebas a pantalla de logueo

    Escenario: Logueo exitoso
      Dado Estoy en pagina de logueo adminfo
      Cuando digito usuario "dfdiaz" y clave "Dfd14z1027.*"
      Y presiono el boton de logueo
      Entonces valido inicio de sesion
      Cuando ingresa al modulo de informes con grupo "11"
      Y me dirijo al informe con nomprograma: "informesprejuridicos"