
export class RegistrationPage{

    clickRegisterButton(){
        cy.get('button[type="submit"]').click()
    }

    firstNameErrorMessage(){
        cy.get('[data-test="first-name-error"]').should('contain', 'First name is required.')
    }

    lastNameErrorMessage(){
        cy.get('[data-test="last-name-error"]').should('contain', 'Last name is required.')
    }

    dateOfBirthErrorMessage(){
        cy.get('[data-test="dob-error"]').should('contain', 'Date of Birth is required.')
    }

    addressErrorMessage(){
        cy.get('[data-test="address-error"]').should('contain', 'Address is required.')
    }

    postcodeErrorMessage(){
        cy.get('[data-test="postcode-error"]').should('contain', 'Postcode is required.')
    }

    cityErrorMessage(){
        cy.get('[data-test="city-error"]').should('contain', 'City is required.')
    }

    stateErrorMessage(){
        cy.get('[data-test="state-error"]').should('contain', 'State is required.')
    }

    contryErrorMessage(){
        cy.get('[data-test="country-error"]').should('contain', 'Country is required.')
    }

    phoneErrorMessage(message){
        cy.get('[data-test="phone-error"]').should('contain', message)
    }

    emailErrorMessage(message){
        cy.get('[data-test="email-error"]').should('contain', message)
    }

    passwordErrorMessage(message){
        cy.get('[data-test="password-error"]').should('contain', message)
    }

    registrationErrorMessage(){
        cy.get('[data-test="register-error"]').should('contain', 'A customer with this email address already exists.')
    }

    inputFirstName(firstName){
        cy.get('#first_name').clear().type(firstName)
    }

    inputLastName(lastName){
        cy.get('#last_name').clear().type(lastName)
    }

    inputDateOfBirth(date){
        cy.get('#dob').clear().type(date)
    }

    inputAddress(address){
        cy.get('#address').clear().type(address)
    }

    inputPostcode(postcode){
        cy.get('#postcode').clear().type(postcode)
    }

    inputCity(city){
        cy.get('#city').clear().type(city)
    }

    inputState(state){
        cy.get('#state').clear().type(state)
    }

    inputContry(country){
        cy.get('#country').select(country)
    }

    inputPhone(phone){
        cy.get('#phone').clear().type(phone)
    }

    inputEmail(email){
        cy.get('#email').clear().type(email)
    }

    inputPassword(password){
        cy.get('#password').clear().type(password)
    }

}

export const registrationPage = new RegistrationPage()