// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add('login', (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })

Cypress.Commands.add('csvToJson', (data) => {
    var lines = data.split('\n');
    var result = [];
    var headers = lines[0].split(';');

    for (var i = 1;i < lines.length;i++) {
      
        var obj = {};
        var currentline = lines[i].split(';');
      
        for (var j = 0;j < headers.length;j++) {
            obj[headers[j]] = currentline[j];
        }
        result.push(obj);
    }

    // console.log(result)
    return result
})