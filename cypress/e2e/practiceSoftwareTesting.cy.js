import { loginPage } from "../page_objects/loginPage"
import { registrationPage } from "../page_objects/registrationPage"
import { homePage } from "../page_objects/homePage"
import { checkOutPage } from "../page_objects/checkoutPage"

const firstName = 'Mileta'
const lastName = 'M'
const dateOfBirth = '1111-11-11'
const address = 'Belgrade'
const postcode = '111'
const city = 'Belgrade'
const state = 'Belgrade'
const country = 'Italy'
const incorrectPhone = 'phone'
const validPhone = '1122'
const incorrectMail = 'abc123'
const invalidMail = 'example@mail.com'
const validMail = 'mileta@mail.com'
const validPassword = '123456'
const invalidPassword = 'abcabc'

describe('Testing registration form', () => {

    beforeEach('open registration page', () => {
        cy.visit('https://practicesoftwaretesting.com/#/auth/register')
    })
    

    it('register empty inputs', () => {
        registrationPage.clickRegisterButton()
        registrationPage.firstNameErrorMessage()
        registrationPage.lastNameErrorMessage()
        registrationPage.dateOfBirthErrorMessage()
        registrationPage.addressErrorMessage()
        registrationPage.postcodeErrorMessage()
        registrationPage.cityErrorMessage()
        registrationPage.stateErrorMessage()
        registrationPage.contryErrorMessage()
        registrationPage.phoneErrorMessage('Phone is required.')
        registrationPage.emailErrorMessage('E-mail is required.')
        registrationPage.passwordErrorMessage('Password is required.')
    })

    it('register invalid inputs', () => {
        registrationPage.inputFirstName(firstName)
        registrationPage.inputLastName(lastName)
        registrationPage.inputDateOfBirth(dateOfBirth)
        registrationPage.inputAddress(address)
        registrationPage.inputPostcode(postcode)
        registrationPage.inputCity(city)
        registrationPage.inputState(state)
        registrationPage.inputContry(country)
        registrationPage.inputPhone(incorrectPhone)
        registrationPage.inputEmail(incorrectMail)
        registrationPage.inputPassword('12345')
        registrationPage.clickRegisterButton()
        registrationPage.phoneErrorMessage('Only numbers are allowed.')
        registrationPage.emailErrorMessage('E-mail format is invalid.')
        registrationPage.passwordErrorMessage('Password must be minimal 6 characters long.')
    })
    
    it('register with valid inputs', () => {
        registrationPage.inputFirstName(firstName)
        registrationPage.inputLastName(lastName)
        registrationPage.inputDateOfBirth(dateOfBirth)
        registrationPage.inputAddress(address)
        registrationPage.inputPostcode(postcode)
        registrationPage.inputCity(city)
        registrationPage.inputState(state)
        registrationPage.inputContry(country)
        registrationPage.inputPhone(validPhone)
        registrationPage.inputEmail(validMail)
        registrationPage.inputPassword(validPassword)
        registrationPage.clickRegisterButton()
        cy.url().should('contain', 'https://practicesoftwaretesting.com/#/auth/login')
    })

    it('registration with already registered email', () => {
        registrationPage.inputFirstName(firstName)
        registrationPage.inputLastName(lastName)
        registrationPage.inputDateOfBirth(dateOfBirth)
        registrationPage.inputAddress(address)
        registrationPage.inputPostcode(postcode)
        registrationPage.inputCity(city)
        registrationPage.inputState(state)
        registrationPage.inputContry(country)
        registrationPage.inputPhone(validPhone)
        registrationPage.inputEmail(validMail)
        registrationPage.inputPassword(validPassword)
        registrationPage.clickRegisterButton()
        registrationPage.registrationErrorMessage()
    })
})

describe('Testing login form', () => {

    beforeEach('open login page', () => {
        cy.visit('https://practicesoftwaretesting.com/#/auth/login')
    })

    it('login with empty fields', () => {
        loginPage.clickOnLogin()
        loginPage.emailErrorMessage('E-mail is required.')
        loginPage.passwordErrorMessage('Password is required.')
    })

    it('login with incorrect email and empty password', () => {
        loginPage.inputEmail(incorrectMail)
        loginPage.clickOnLogin()
        loginPage.emailErrorMessage('E-mail format is invalid.')
        loginPage.passwordErrorMessage('Password is required.')
    })

    it('login with invalid email and empty password', () => {
        loginPage.inputEmail(invalidMail)
        loginPage.clickOnLogin()
        loginPage.passwordErrorMessage('Password is required.')
    })

    it('login with valid email and empty password', () => {
        loginPage.inputEmail(validMail)
        loginPage.clickOnLogin()
        loginPage.passwordErrorMessage('Password is required.')
    })

    it('login with empty email and invalid password', () => {
        loginPage.inputPassword(invalidPassword)
        loginPage.clickOnLogin()
        loginPage.emailErrorMessage('E-mail is required.')
    })
   
    it('login with empty email and valid password', () => {
        loginPage.inputPassword(validPassword)
        loginPage.clickOnLogin()
        loginPage.emailErrorMessage('E-mail is required.')
    })

    it('login with incorrect email and invalid password', () => {
        loginPage.inputEmail(incorrectMail)
        loginPage.inputPassword(invalidPassword)
        loginPage.clickOnLogin()
        loginPage.emailErrorMessage('E-mail format is invalid.')
    })

    it('login with invalid email and invalid password', () => {
        loginPage.inputEmail(invalidMail)
        loginPage.inputPassword(invalidPassword)
        loginPage.clickOnLogin()
        loginPage.loginErrorMessage('Invalid email or password')
    })

    it('login with valid email and invalid password', () => {
        loginPage.inputEmail(validMail)
        loginPage.inputPassword(invalidPassword)
        loginPage.clickOnLogin()
        loginPage.loginErrorMessage('Invalid email or password')
    })

    it('login with incorrect email and valid password', () => {
        loginPage.inputEmail(incorrectMail)
        loginPage.inputPassword(validPassword)
        loginPage.clickOnLogin()
        loginPage.emailErrorMessage('E-mail format is invalid.')
    })

    it('login with invalid email and valid password', () => {
        loginPage.inputEmail(invalidMail)
        loginPage.inputPassword(validPassword)
        loginPage.clickOnLogin()
        loginPage.loginErrorMessage('Invalid email or password')
    })

    it('login with valid email and one character password', () => {
        loginPage.inputEmail(validMail)
        loginPage.inputPassword('a')
        loginPage.clickOnLogin()
        loginPage.passwordErrorMessage('Password length is invalid')
    })

    it('login with valid email and two character password', () => {
        loginPage.inputEmail(validMail)
        loginPage.inputPassword('ab')
        loginPage.clickOnLogin()
        loginPage.passwordErrorMessage('Password length is invalid')
    })

    it('login with valid email and three character password', () => {
        loginPage.inputEmail(validMail)
        loginPage.inputPassword('abc')
        loginPage.clickOnLogin()
        loginPage.passwordErrorMessage('Password length is invalid')
    })

    it('login with valid email and valid password', () => {
        loginPage.inputEmail(validMail)
        loginPage.inputPassword(validPassword)
        loginPage.clickOnLogin()
        //cy.url().should('contain', 'https://practicesoftwaretesting.com/#/account')
        cy.get('h1').should('contain', 'My account')
    })

})

describe('Search and filter for products', () => {

    beforeEach('open home page', () => {
        cy.visit('https://practicesoftwaretesting.com/#/')
    })

    it('search for products using search bar', () => {
        homePage.searchBar('hammer')
    })

    it('search for products using search bar', () => {
        homePage.searchBar('saw')
    })

    it('search for products using checkbox', () => {
        homePage.filterProductsCheckbox(1)
        homePage.filterProductsCheckbox(0)
        homePage.filterProductsCheckbox(5)
    })

    it('sort products', () => {
        homePage.sortByNameAZ()
        homePage.sortByPriceHighLow()
        homePage.sortByPriceLowHigh()
        homePage.sortByNameZA()
    })

    it('filter products with slider', () => {
        homePage.slideRightMinPrice(20)
        homePage.slideLeftMinPrice(10)
        homePage.slideLeftMaxPrice(10)
        homePage.slideRightMaxPrice(20)
    })
})

describe.skip('Add products to the cart and checkout', () => {

    beforeEach('open home page', () => {
        cy.visit('https://practicesoftwaretesting.com/#/')
    })

    it('add products to the cart and pay', () => {
        homePage.addProductsToCartAndVerify('Wood Saw', 'Cordless Drill 20V', 'Bolt Cutters')
        checkOutPage.clickOnCartAndCheckout()
    })
})
