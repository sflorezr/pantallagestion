# language: es
# Autor: sflorez@solati.com.co

  Característica: Pruebas a pantalla de logueo

    Escenario: Logueo exitoso
      Dado Estoy en pagina de logueo adminfo
      Cuando digito usuario "dfdiaz" y clave "Dfd14z1027.*"
      Y presiono el boton de logueo
      Entonces valido inicio de sesion
      ## 15888 es software.consecutivo del usuario y bd con la que haremos la prueba par
      Y Selecciona base de datos "15888"
      Cuando ingresa al modulo de informes con grupo "1"
      Y me dirijo al informe con nomprograma: "informesprejuridicos"