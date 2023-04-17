const json = require('../../fixtures/Booking.json')


json.forEach((jsonBody) => {
describe('Testes do restful booker', () => {
    let bookingId
    let tempoDeRespostaPadrao = 300
    let token
    
        
        context('Ping', () => {
            it('Ping', () => {
                cy.request('get', '/ping')
                    .then(({status, duration, body}) => {
                        expect(status, 'Status code').to.eq(201)
                        expect(duration, 'Tempo de resposta').to.be.lessThan(tempoDeRespostaPadrao)
                        expect(body, 'Corpo da resposta').to.contain('Created')
                    })
            })
        })
    
        context('Criação de Booking', () => {
            it(`${jsonBody.firstname} ${jsonBody.lastname}`, () => {
                console.log('jsonBody: ' + jsonBody)
                let headers = {
                    method: 'POST', 
                    url: '/booking',
                    body : jsonBody,
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8',
                    }
                }

                cy.request(headers)
                    .then(({status, duration, body}) => {
                        expect(status, 'Status code').to.eq(200)
                        expect(duration, 'Tempo de resposta').to.be.lessThan(tempoDeRespostaPadrao)
                        expect(body.booking.firstname, 'Primeiro nome').to.eq(jsonBody.firstname)
                        expect(body.booking.lastname, 'Último nome').to.eq(jsonBody.lastname)
                        bookingId = body.bookingid
                    })
            });
        })

        context('Verificação de Booking', () => {

            it(`${jsonBody.firstname} ${jsonBody.lastname}`, () => {
                cy.request('get',`/booking/${bookingId}`)
                    .then(({duration, body, status}) => {
                        expect(status, 'Status code').to.eq(200)
                        expect(duration, 'Tempo de resposta').to.be.lessThan(tempoDeRespostaPadrao)
                        expect(body.firstname, 'Primeiro nome').to.eq(jsonBody.firstname)
                        expect(body.lastname, 'Último nome').to.eq(jsonBody.lastname)
                    })
            })
        })

        context('Atualização de Booking', () => {
            beforeEach(() => {
                let headers = {
                    method: 'POST', 
                    url: '/auth',
                    body : {
                        username: 'admin',      // Hardcoded no aplicativo
                        password: 'password123'
                    },
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8',
                    }
                }
            
                cy.request(headers)
                    .then(({duration, body, status}) => {
                        expect(status, 'Status code').to.eq(200)
                        expect(duration, 'Tempo de resposta').to.be.lessThan(tempoDeRespostaPadrao)
                        token = body.token
                    })
                
            })
            

            it(`${jsonBody.firstname} ${jsonBody.lastname}`, () => {
                    
                let  headers = {
                    method: 'PUT', 
                    url: `/booking/${bookingId}`,
                    body : jsonBody,
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8',
                        'cookie': `token=${token}`
                    }
                }

                cy.request('get', `/booking?firstname=${jsonBody.firstname}&lastname=${jsonBody.lastname}`)
                    .then(({status, body, duration}) => {
                        expect(status).to.eq(200)
                        expect(body).to.not.be.null
                        expect(duration, 'Tempo de resposta').to.be.lessThan(tempoDeRespostaPadrao)
                        bookingId = body[0].bookingid
                            
                    })
                
                cy.request(headers)
                    .then(({duration, body, status}) => {
                        expect(status, 'Status code').to.eq(200)
                        expect(duration, 'Tempo de resposta').to.be.lessThan(tempoDeRespostaPadrao)
                        expect(body.firstname, 'Primeiro nome').to.eq(jsonBody.firstname)
                        expect(body.lastname, 'Último nome').to.eq(jsonBody.lastname)
                    })
            })

        })
        context('Deleção de Booking', () => {
            beforeEach(() => {
                let headers = {
                    method: 'POST', 
                    url: '/auth',
                    body : {
                        username: 'admin',      // Hardcoded no aplicativo
                        password: 'password123'
                    },
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8',
                    }
                }
            
                cy.request(headers)
                    .then(({duration, body, status}) => {
                        expect(status, 'Status code').to.eq(200)
                        expect(duration, 'Tempo de resposta').to.be.lessThan(tempoDeRespostaPadrao)
                        token = body.token
                    })
                
                cy.request('get', `/booking?firstname=${jsonBody.firstname}&lastname=${jsonBody.lastname}`)
                    .then(({status, body, duration}) => {
                        expect(status).to.eq(200)
                        expect(body).to.not.be.null
                        expect(duration, 'Tempo de resposta').to.be.lessThan(tempoDeRespostaPadrao)
                        bookingId = body[0].bookingid
                    
                    })
            })
            it(`${jsonBody.firstname} ${jsonBody.lastname}`, () => {
    
                let  headers = {
                    method: 'DELETE', 
                    url: `/booking/${bookingId}`,
                    body : jsonBody,
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8',
                        'cookie': `token=${token}`
                    }
                }
                
                cy.request(headers)
                    .then(({duration, body, status}) => {
                        expect(status, 'Status code').to.eq(201)
                        expect(duration, 'Tempo de resposta').to.be.lessThan(tempoDeRespostaPadrao)
                        expect(body).to.contain('Created')
                    })
            })
        })
    })
})