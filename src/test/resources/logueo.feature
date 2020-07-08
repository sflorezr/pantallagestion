# language: es
# Autor: sflorez@solati.com.co

  Característica: Pruebas a pantalla de logueo

    Escenario: Logueo exitoso
      Dado Estoy en pagina de logueo adminfo
      Cuando digito usuario "robot64" y clave "987654384"
      Y presiono el boton de logueo
      Entonces valido inicio de sesion