import React from 'react'
const style = {
  background:"no-repeat center",
  backgroundSize:"contain",
  width:"100%",
}
const MidContainerItem = (props) => {
  return (
    <div className='midContainer'>
      <div className="containerImg">
        <img src={props.src} alt="myimage"  style={style}/>
      </div>
      <div className="containerBody">
        <h2 className="boxHeader">
          {props.header}
        </h2>
        <p className="boxContent">
          {props.content}
        </p>
      </div>
    </div>
  )
}

export default MidContainerItem
