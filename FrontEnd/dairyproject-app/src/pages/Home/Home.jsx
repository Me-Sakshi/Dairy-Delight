import React from 'react'
import './Home.css'
import BottomContainer from './subContainer/Bottom/BottomContainer'
import MidContainer from './subContainer/Mid/MidContainer'
import UncontrolledExample from './subContainer/Slider/UncontrolledExample'

const Home = () => {
  return (
    <div className='home' >
      <div className='slider'>
        <UncontrolledExample />
      </div>
      <MidContainer />
      <BottomContainer />
    </div>
  )
}

export default Home
