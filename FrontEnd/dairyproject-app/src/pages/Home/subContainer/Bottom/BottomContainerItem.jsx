import React from 'react'

const BottomContainerItem = (props) => {
  return (
    <div className='item'>
        <div className='item_img'>
            <h1>
                {props.header}
            </h1>
            <img src={props.src} alt="temp" />
        </div>
        <div className='item_info'>
            <div className="header">
                    {props.header}
            </div>
            <div className="desc">
                {props.desc}
            </div>
        </div>
        
    </div>
  )
}

export default BottomContainerItem
