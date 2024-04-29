import React from 'react'

export default function Navbar() {
  return (
    <nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand">CRUD</a>
      <form class="d-flex">
        <button className='btn btn-outline-primary mx-2'>+Add</button>
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"/>
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </nav>
  )
}
