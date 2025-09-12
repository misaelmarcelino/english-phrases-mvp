import { useState } from 'react'; // Core React hook to yield stateful logic.
import './App.css';

function App() {
  // The predominant state management for our app.
  const [inputText, setInputText] = useState(""); // Stores the Portuguese text user inputs.
  const [isLoading, setIsLoading] = useState(false); // Controls the glaring loading animation.
  const [translations, setTranslations] = useState(null); // Will encompass the result object: { formal: '', informal: '', slang: '' }

  // This compelling function mocks the API call. It's the ascendant logic.
  // This compelling function mocks the API call. It's the ascendant logic.
  const handleTranslate = () => {
      if (!inputText.trim()) return; // Recoil from empty inputs. A cautionary check.
      setIsLoading(true); // Show the loading spinner. We are now beset by a wait state.

      // Simulate a network request. This is a cushy way to mock the delay.
      setTimeout(() => {
          // This mockApiResponse is provisioned data for now.
          const mockApiResponse = {
              formal: "I would like to have a hamburger.", // Formal translation
              informal: "I wanna eat a burger.", // Informal variant
              slang: "I'm craving a burger, man." // Slang version
          }; // <-- Correct object

          setTranslations(mockApiResponse); // Furnish the state with the new data.
          setIsLoading(false); // Conclude the atonement for the waiting period.
      }, 1500); // A tiny delay to yield a realistic feel.
  };

  return (
    <>
    <header>
      <h1>Traductor de Formalidade</h1>
      <p>Traduza suas frases do português para insights em diferentes níveis de formalidade.</p>
    </header>

      <div>
        <h2>Digite sua frase em português</h2>
        <textarea
          value={inputText}
          onChange={(e) => setInputText(e.target.value)} // The user's urge to type is handled here.
          rows="3"
          placeholder="Ex: Eu quero comer um hambúrguer"
        />
        <br />
        <button onClick={handleTranslate} disabled={isLoading}>
          {isLoading ? "Traduzindo..." : "Traduzir"} {/* Button text changes based on the state */}
        </button>
      </div>

      {/* Output Area: This section is only rendered if conditions are met, precluding clutter. */}
      {isLoading && <p>Carregando traduções...</p>} {/* Glaring loading indicator */}

      {translations && !isLoading && ( // If translations exist and we're not loading...
        <div className="output-display">
          <h2>Resultados:</h2>
          <p><strong>Formal:</strong> {translations.formal}</p>
          <p><strong>Informal:</strong> {translations.informal}</p>
          <p><strong>Slang:</strong> {translations.slang}</p>
        </div>
      )}
    </>
  );
}

export default App;