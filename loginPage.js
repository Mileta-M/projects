
export class LoginPage{

    clickOnLogin(){
        cy.get('form').submit()
    }

    inputEmail(email){
        cy.get('#email').clear().type(email)
    }

    inputPassword(password){
        cy.get('#password').clear().type(password)
    }

    emailErrorMessage(message){
        cy.get('[data-test="email-error"]').should('contain', message)
    }

    passwordErrorMessage(message){
        cy.get('[data-test="password-error"]').should('contain', message)
    }

    loginErrorMessage(message){
        cy.get('[data-test="login-error"]').should('contain', message)
    }


}

export const loginPage = new LoginPage()