
export class CheckOutPage{

    clickOnCartAndCheckout(){
        cy.get('[data-test="nav-cart"]').click()
        cy.get('[data-test="proceed-1"]').click()
        cy.get('#email').type('customer@practicesoftwaretesting.com')
        cy.get('#password').type('welcome01')
        cy.get('[type="submit"]').click()
        cy.get('[data-test="proceed-2"]').click()
        this.inputBillingAddress()
        cy.get('[data-test="proceed-3"]').click()
        cy.get('select').select(1)
        cy.get('#account-name').type('11')
        cy.get('#account-number').type('11')
        cy.get('[data-test="finish"]').click()
        cy.get('#order-confirmation').should('contain', 'Thanks for your order! Your invoice number is')
    }

    inputBillingAddress(){
        cy.get('#address').clear().type('Belgrade')
        cy.get('#city').clear().type('Belgrade')
        cy.get('#state').clear().type('Belgrade')
        cy.get('#country').clear().type('Belgrade')
        cy.get('#postcode').clear().type('Belgrade')
    }

    

}

export const checkOutPage = new CheckOutPage()