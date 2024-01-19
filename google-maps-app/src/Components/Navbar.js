import React from 'react';
import './Navbar.css';

const Navbar = () => {
  return (
    <nav className="navbar">
      <div className="navbar-container">
        <div to="/" className="navbar-logo">
          CodExist
        </div>
      </div>
    </nav>
  );
};

export default Navbar;