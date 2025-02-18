import React from 'react'
import MidContainerItem from './MidContainerItem'
const items = [
  {
    header: "Milking Twice a Day",
    content: "Fresh Milk reaches your doorstep within 24-48 hours",
    src: "./assets/images/milking.webp"
  },
  {
    header: "Tested Milk Prodcuts",
    content: "Scientific quality testing of milk for adulteration at every stage",
    src: "./assets/images/testing.png"
  },
  {
    header: "Pasteurization & Packing at 3°C",
    content: "Maintaining milk at 3°C improves shelf life prevents increase in bacteria counts",
    src: "./assets/images/pachtrization.png"
  },
  {
    header: "Doorstep Delivery",
    content: "Fresh Milk promise with added convenience of home delivery",
    src: "./assets/images/homedelivery.jpg"
  },
  {
    header: "Hassle Free Management",
    content: "Easy to use platform for managing your daily orders easily",
    src: "./assets/images/heaslefree.png"
  },
  {
    header: "Nearby Seller",
    content: "Choose a nearby, reliable local supplier for the quickest delivery",
    src: "./assets/images/nearbyseller.webp"
  },
]
const Items = () => {
  return (
    <div className='mainContainer'>
      <h1 className="mainHeader" style={{ marginTop: 32 }}>
        Why Dairy Milk Services ?
      </h1>
      <h2 className="subHeader">
        We make sure every product you get has been fully tested for purity and freshness.
      </h2>
      <div className='subItems'>
        {items.map((item, id) => <MidContainerItem key={id} header={item.header} content={item.content} src={item.src} />
        )}
      </div>
    </div>
  )
}

export default Items
