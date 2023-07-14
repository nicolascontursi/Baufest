Feature: Automatización de compra en demoblaze.com

  Scenario: Compra de una laptop

    Given El Usuario se encuentra en la Pagina Principal
    And Hace click en el botón Sign Up
    And Rellena el formulario de registro con usuario y contraseña
    When Hace click en el boton de registro
    And Inicia sesión con usuario y contraseña
    And Agrega una laptop al carrito
    Then Verifica que se haya agregado al carrito