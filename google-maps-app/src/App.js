import React, { useState } from 'react';
import GoogleMapView from './Components/GoogleMapView';
import FormExample from './Components/Form';
import './App.css';
import Navbar from './Components/Navbar';

function App() {
  const [centerData, setCenterData] = useState(null);

  const handleFormSubmit = (data) => {
    setCenterData(data);
  };

  return (
    <>
      <Navbar />
      <div>
        <FormExample onFormSubmit={handleFormSubmit} />
      </div>

      <div className='position'>
        <div className='half-screen-container outer-container'>
          <GoogleMapView centerData={centerData} />
        </div>
      </div>
    </>
  );
}

export default App;
