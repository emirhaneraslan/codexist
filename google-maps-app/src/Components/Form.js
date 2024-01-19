import React, { useState } from 'react';
import './Form.css';
import axios from 'axios';

const Form = ({ onFormSubmit }) => {
  const [formData, setFormData] = useState({
    langitude: '',
    latitude: '',
    radius: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
  e.preventDefault();

  const longitude = formData.langitude !== '' ? parseFloat(formData.langitude) : 0;
  const latitude = formData.latitude !== '' ? parseFloat(formData.latitude) : 0;
  const radius = formData.radius !== '' ? parseInt(formData.radius) : 0;

  try {
    const response = await axios.get('http://localhost:8070/api/v1/map/position', {
      params: {
          longitude: longitude,
          latitude: latitude,
          radius: radius,
        },
      });

      if (response.data.success) {
        console.log('Spring Boot Response:', response.data);
        alert(response.data.message);

      onFormSubmit({
        longitude: response.data.data.longitude,
        latitude: response.data.data.latitude,
        radius: response.data.data.radius,
      });

      } else {
        console.error('Invalid coordinates:', response.data.message);
        alert(response.data.message);
      }

      console.log('Spring Boot Response:', response.data);

    } catch (error) {
      console.error('Error submitting form:', error);
    }
  };

  return (
    <form className="form-container" onSubmit={handleSubmit}>
      <label>
        Langitude:
        <input
          type="text"
          name="langitude"
          placeholder='34.6766'
          value={formData.langitude}
          onChange={handleChange}
          required
        />
      </label>
      <label>
        Latitude :
        <input
          type="text"
          name="latitude"
          placeholder='37.9698'
          value={formData.latitude}
          onChange={handleChange}
          required
        />
      </label>
      <label>
        Radius:
        <input
          type="text"
          name="radius"
          placeholder='5000'
          value={formData.radius}
          onChange={handleChange}
        />
      </label>
      <button type="submit">Search</button>
    </form>
  );
};

export default Form;