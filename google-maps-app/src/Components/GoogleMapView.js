import React, { useEffect } from 'react';
import { GoogleMap, useLoadScript, Marker, Circle } from '@react-google-maps/api';


const mapContainerStyle = {
  width: '100vh',
  height: '100vh',
};

const GoogleMapView = ({ centerData }) => {
  const { isLoaded } = useLoadScript({
    googleMapsApiKey: 'AIzaSyBhMPmt_reCrGnq6COE4x0L8JlvXA3Dcf8',
    
  });

  useEffect(() => {
    if (isLoaded && centerData) {
      const { latitude, longitude, radius } = centerData;
  
      if (isNaN(latitude) || isNaN(longitude) || isNaN(radius)) {
        console.error('Invalid coordinates:', centerData);
        return;
      }
  
      setMapOptions((prevOptions) => ({
        ...prevOptions,
        center: { lat: parseFloat(latitude), lng: parseFloat(longitude) },
        radius: parseInt(radius, 10),
      }));
    }
  }, [isLoaded, centerData]);

  const [mapOptions, setMapOptions] = React.useState({
    center: { lat: 37.9698, lng: 34.6766 },
    radius: 50000,
  });

  if (!isLoaded) {
    return <div>Loading maps</div>;
  }

  return (
    <div>
      <GoogleMap mapContainerStyle={mapContainerStyle} zoom={10} center={mapOptions.center}>
        {centerData && (
          <>
            <Marker position={{ lat: mapOptions.center.lat, lng: mapOptions.center.lng }} />
            <Circle
              center={{ lat: mapOptions.center.lat, lng: mapOptions.center.lng }}
              radius={mapOptions.radius}
              
            />
          </>
        )}
      </GoogleMap>
    </div>
  );
};

export default GoogleMapView;
