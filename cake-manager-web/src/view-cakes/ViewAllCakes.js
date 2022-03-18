import React, { useEffect, useState } from 'react'
import { Container, Table } from 'reactstrap'

const ViewAllCakes = () => {
  const [cakes, setCakes] = useState([])

  useEffect(async () => {
    const response = await fetch('/api/v1/cakes')
    const cakesResponse = await response.json()
    setCakes(cakesResponse.cakes)
  }, [])

  return (
    <>
      <Container fluid>
        <h3>Cakes</h3>
        <Table className='mt-4'>
          <thead>
            <tr>
              <th>Name</th>
              <th>Description</th>
              <th>Image</th>
            </tr>
          </thead>
          <tbody>
            {cakes.map((cake) => (
              <tr key={cake.id}>
                <td>{cake.title}</td>
                <td>{cake.desc}</td>
                <td>
                  <img width={200} height={200} src={cake.image} alt={cake.image} />
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </Container>
    </>
  )
}

export default ViewAllCakes
