import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import Resident from './addResident/Resident'
import './App.css'
import Visitor from './addVisitor/Visitor'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <div>
        <Resident/>
        <Visitor/>
       
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  )
}

export default App
