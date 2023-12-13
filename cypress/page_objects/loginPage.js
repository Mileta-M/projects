
export class LoginPage{

    constructor(){
        this.email = '#email'
        this.password = '#password'
        this.emailError = '[data-test="email-error"]'
        this.passwordError = '[data-test="password-error"]'
        this.loginError = '[data-test="login-error"]'
        this.buttonSubmit = '.btnSubmit'
    }

    clickOnLogin(){
        cy.get(this.buttonSubmit).click()
    }

    inputEmail(email){
        cy.get(this.email).clear().type(email)
    }

    inputPassword(password){
        cy.get(this.password).clear().type(password)
    }

    emailErrorMessage(message){
        cy.get(this.emailError).should('contain', message)
    }

    passwordErrorMessage(message){
        cy.get(this.passwordError).should('contain', message)
    }

    loginErrorMessage(message){
        cy.get(this.loginError).should('contain', message)
    }

}

export const loginPage = new LoginPage()