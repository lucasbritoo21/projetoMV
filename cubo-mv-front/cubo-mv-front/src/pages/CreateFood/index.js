import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import api from "../../services/api";
import './styles.css'
// import { Container } from './styles';
const INITIAL_STATE = {
  name: "",
};
function CreateFood() {
  const [formFields, setFormFields] = useState(INITIAL_STATE);
  const [currentCpf, setCurrentCpf] = useState(INITIAL_STATE);
  const [foods, setFoods] = useState([]);
  let { id } = useParams();

  const getFoods = async () => {
    try {
      const response = await api.get(`/foods`);
      const { foods } = response.data._embedded;
      setFoods(foods);
      console.log("TEM USER", foods);
    } catch (error) {
      console.log(error);
    }
  };
  useEffect(() => {
    getFoods();
    // return () => {
    //   cleanup
    // }
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormFields((prev) => {
      return { ...prev, [name]: value };
    });
  };

  const createFood = async (e) => {
    e.preventDefault();
    try {
      const response = await api.post(`/foods`, formFields);
      console.log("REPOSTA", response);
      const data = response.data;

      setFoods((prev) => [...prev, data]);
      setFormFields(INITIAL_STATE);
    } catch (error) {}
  };
  return (
    <div className="form">
      <form onSubmit={createFood}>
        <div className="form__input">
          <label>Nome</label>
          <input
            type="text"
            name="name"
            value={formFields.name}
            onChange={handleChange}
          />
        </div>

       

        <div className="buttonWraper">
          <button className="button" type="submit">
          Criar comida
          </button>
          <Link to="/" className="button">
            Voltar
          </Link>
        </div>
      </form>
      <div className="foodList">
        {foods.map((food) => (
          <div className="foodList_item">{food.name}</div>
        ))}
      </div>
    </div>
  );
}

export default CreateFood;
