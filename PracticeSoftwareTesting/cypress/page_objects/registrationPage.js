
export class RegistrationPage{

    constructor(){
        this.firstNameInput = '#first_name'
        this.lastNameInput = '#last_name'
        this.dateOfBirthInput = '#dob'
        this.addressInput = '#address'
        this.postcodeInput = '#postcode'
        this.cityInput = '#city'
        this.stateInput = '#state'
        this.countryInput = '#country'
        this.phoneInput = '#phone'
        this.emailInput = '#email'
        this.passwordInput = '#password'
        this.buttonRegister = 'button[type="submit"]'
        this.firstNameError = '[data-test="first-name-error"]'
        this.lastNameError = '[data-test="last-name-error"]'
        this.dateOfBirthError = '[data-test="dob-error"]'
        this.addressError = '[data-test="address-error"]'
        this.postcodeError = '[data-test="postcode-error"]'
        this.cityError = '[data-test="city-error"]'
        this.stateError = '[data-test="state-error"]'
        this.contryError = '[data-test="country-error"]'
        this.phoneError = '[data-test="phone-error"]'
        this.emailError = '[data-test="email-error"]'
        this.passwordError = '[data-test="password-error"]'
        this.registrationError = '[data-test="register-error"]'
    }
    
    inputFirstName(firstName){
        cy.get(this.firstNameInput).clear().type(firstName)
    }

    inputLastName(lastName){
        cy.get(this.lastNameInput).clear().type(lastName)
    }

    inputDateOfBirth(date){
        cy.get(this.dateOfBirthInput).clear().type(date)
    }

    inputAddress(address){
        cy.get(this.addressInput).clear().type(address)
    }

    inputPostcode(postcode){
        cy.get(this.postcodeInput).clear().type(postcode)
    }

    inputCity(city){
        cy.get(this.cityInput).clear().type(city)
    }

    inputState(state){
        cy.get(this.stateInput).clear().type(state)
    }

    inputContry(country){
        cy.get(this.countryInput).select(country)
    }

    inputPhone(phone){
        cy.get(this.phoneInput).clear().type(phone)
    }

    inputEmail(email){
        cy.get(this.emailInput).clear().type(email)
    }

    inputPassword(password){
        cy.get(this.passwordInput).clear().type(password)
    }

    clickRegisterButton(){
        cy.get(this.buttonRegister).click()
    }

    firstNameErrorMessage(){
        cy.get(this.firstNameError).should('contain', 'First name is required.')
    }

    lastNameErrorMessage(){
        cy.get(this.lastNameError).should('contain', 'Last name is required.')
    }

    dateOfBirthErrorMessage(){
        cy.get(this.dateOfBirthError).should('contain', 'Date of Birth is required.')
    }

    addressErrorMessage(){
        cy.get(this.addressError).should('contain', 'Address is required.')
    }

    postcodeErrorMessage(){
        cy.get(this.postcodeError).should('contain', 'Postcode is required.')
    }

    cityErrorMessage(){
        cy.get(this.cityError).should('contain', 'City is required.')
    }

    stateErrorMessage(){
        cy.get(this.stateError).should('contain', 'State is required.')
    }

    contryErrorMessage(){
        cy.get(this.contryError).should('contain', 'Country is required.')
    }

    phoneErrorMessage(message){
        cy.get(this.phoneError).should('contain', message)
    }

    emailErrorMessage(message){
        cy.get(this.emailError).should('contain', message)
    }

    passwordErrorMessage(message){
        cy.get(this.passwordError).should('contain', message)
    }

    registrationErrorMessage(){
        cy.get(this.registrationError).should('contain', 'A customer with this email address already exists.')
    }

}

export const registrationPage = new RegistrationPage()