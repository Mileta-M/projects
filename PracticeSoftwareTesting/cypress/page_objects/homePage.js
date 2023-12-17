
export class HomePage{

    searchBar(product){
        cy.get('[data-test="search-query"]').type(product)
        cy.get('[type="submit"]').click()
        cy.wait(900)
        cy.get('h5').each( title => {
            const newTite = title.text().toLowerCase()
            const newProduct = product.toLowerCase()
            cy.wrap(newTite).should('contain', newProduct)
        })
        //cy.get('[data-test="search-query"]').should('have.value', product)
    }

    clickOnResetSearch(){
        cy.get('[type="reset"]').click()
    }

    filterProductsCheckbox(number){
        cy.get('.checkbox').find('label').contains('Hand Tools').should('be.visible')
        cy.get('.checkbox')
            .find('input')
            .eq(number)
            .check()
            .should('be.checked')
    }

    sortByNameAZ(){
        cy.get('.form-select').select(1).should('have.value', 'name,asc')
    }

    sortByNameZA(){
        cy.get('.form-select').select(2).should('have.value', 'name,desc')
    }

    sortByPriceHighLow(){
        cy.get('.form-select').select(3).should('have.value', 'price,desc')
    }

    sortByPriceLowHigh(){
        cy.get('.form-select').select(4).should('have.value', 'price,asc')
    }
    
    slideLeftMinPrice(steps){
        const left = '{leftarrow}'.repeat(steps)
        cy.get('span.ngx-slider-model-value').invoke('text').then( value => {
            const number = parseInt(value, 10)
            let result = number - 2*steps
            cy.get('.ngx-slider-pointer-min').type(left)
            cy.get('span.ngx-slider-model-value').should('contain', result)
        })
    }

    slideRightMinPrice(steps){
        const right = '{rightarrow}'.repeat(steps)
        cy.get('span.ngx-slider-model-value').invoke('text').then( value => {
            const number = parseInt(value, 10)
            let result = number + 2*steps
            cy.get('.ngx-slider-pointer-min').type(right)
            cy.get('span.ngx-slider-model-value').should('contain', result)
        })
        
    }

    slideLeftMaxPrice(steps){
        const left = '{leftarrow}'.repeat(steps)
        cy.get('span.ngx-slider-model-high').invoke('text').then( value => {
            const number = parseInt(value, 10)
            let result = number - 2*steps
            cy.get('.ngx-slider-pointer-max').type(left)
            cy.get('span.ngx-slider-model-high').should('contain', result)
        })
    }

    slideRightMaxPrice(steps){
        const right = '{rightarrow}'.repeat(steps)
        cy.get('span.ngx-slider-model-high').invoke('text').then( value => {
            const number = parseInt(value, 10)
            let result = number + 2*steps
            cy.get('.ngx-slider-pointer-max').type(right)
            cy.get('span.ngx-slider-model-high').should('contain', result)
        })
    }

    addProductsToCartAndVerify(product1, product2, product3){
        this.clickOnResetSearch()
        cy.wait(1500)
        const products = [product1, product2, product3]
        cy.wrap(products).each( prod => {
            cy.get('[data-test="search-query"]').type(prod)
            cy.get('[type="submit"]').click()
            cy.wait(900)
            cy.get('h5').contains(prod).click()
            cy.get('#btn-add-to-cart').click()
            cy.go(-1)
            this.clickOnResetSearch()
            cy.wait(1500)
        })
        cy.wait(2000)
        cy.get('[data-test="nav-cart"]').click()
        cy.wait(3000)
        cy.get('tbody tr').eq(0).find('td').find('span').should('contain', product1)
        cy.get('tbody tr').eq(1).find('td').find('span').should('contain', product2)
        cy.get('tbody tr').eq(2).find('td').find('span').should('contain', product3)
    }
}

export const homePage = new HomePage()
