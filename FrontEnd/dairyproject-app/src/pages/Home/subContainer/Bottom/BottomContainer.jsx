import React from 'react'
import BottomContainerItem from './BottomContainerItem'
import './BottomContainer.css'
const productInfo = [
    {
        header: "The Natural Sweetness of Milk ",
        desc: "A glass of milk may become your kid's favourite drink with our Milk. The natural sweetness of our milk is the hallmark of purity and becomes an instant favourite among kids. Moreover, we source our milk from a mix of Desi Gir cows, desi Sahiwal cows, crossbred Holstein & Jersey cows directly from the farmers, run it through 70 stringent tests for impurities and toxins and deliver it to you within 24-36 hours of milking. No preservatives, no milk powder or cream is added to the milk and the milk is not recombined in any form. We deliver the milk under the best cold chain right up to your doorstep. Health benefits: The milk you receive has an abundance of Vitamin D, B, B12, Calcium, Magnesium, Phosphorus, Potassium and amino acids - a combination of powerful nutrients essential for the development of your mind and body.",
        src: "./assets/images/milk.jpg"

    },
    {
        header: "Every Scoop Tastes Like Home",
        desc: "Product that we have made in collaboration with thousands of farmers. Understanding the age-old practice of Dahi making at home, we worked further on it to set the richest, creamiest and tastiest Dahi that every family would love to consume. Every scoop of our product is 100% natural, made from pure milk and delivered to your doorstep.",
        src: "./assets/images/curd.jpg"
    },
    {
        header: "Fresh Paneer Made From Cow Milk ",
        desc: "Our Taaza paneer stands true to its name - it is delectably fresh and it melts in your mouth! We curated this delectable experience for you through continuous feedback from hundreds of our customers. The paneer is made from farm-fresh cow milk and in the most natural home-based paneer making techniques. We then pack and deliver it to your doorstep fresh.",
        src: "./assets/images/freshpaneer.jpg"
    },

]

const BottomContainer = () => {
    return (
        <div className='product'>
            {
                productInfo.map((item, id) => <BottomContainerItem key={id} header={item.header} desc={item.desc} src={item.src} />)
            }
        </div>
    )
}

export default BottomContainer
