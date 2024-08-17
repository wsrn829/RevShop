import React, { useState, FormEvent, useEffect } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

interface Message {
  sender: 'user' | 'ai';
  text: string;
}

// Declare types for SpeechRecognition and SpeechRecognitionEvent
declare global {
  interface Window {
    SpeechRecognition: any;
    webkitSpeechRecognition: any;
  }
}

const AIChatBot: React.FC = () => {
  const [prompt, setPrompt] = useState<string>('');
  const [messages, setMessages] = useState<Message[]>([]);
  const [recognition, setRecognition] = useState<any>(null);
  const [isSTTUsed, setIsSTTUsed] = useState<boolean>(false);

  // Initialize Speech Recognition
  useEffect(() => {
    const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
    if (SpeechRecognition) {
      const recognitionInstance = new SpeechRecognition();
      recognitionInstance.continuous = false;
      recognitionInstance.interimResults = false;
      recognitionInstance.lang = 'en-US';

      recognitionInstance.onresult = (event: any) => {
        const transcript = event.results[0][0].transcript;
        setPrompt(transcript);
        setIsSTTUsed(true);
      };

      setRecognition(recognitionInstance);
    } else {
      console.error('Speech Recognition API not supported in this browser.');
    }
  }, []);

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    const userMessage: Message = { sender: 'user', text: prompt };
    setMessages([...messages, userMessage]);

    try {
      const res = await axios.get('http://localhost:7777/api/ai/chat', {
        params: { prompt }
      });
      const aiMessage: Message = { sender: 'ai', text: res.data };
      setMessages([...messages, userMessage, aiMessage]);
      if (isSTTUsed) {
        speakText(res.data);
        setIsSTTUsed(false);
      }
    } catch (error) {
      console.error('Error fetching AI response:', error);
    }
    setPrompt('');
  };

  const startRecognition = () => {
    if (recognition) {
      recognition.start();
    }
  };

  const speakText = (text: string) => {
    const utterance = new SpeechSynthesisUtterance(text);
    utterance.lang = 'en-US';
    window.speechSynthesis.speak(utterance);
  };

  return (
    <div className="container mt-5">
      <div className="card">
        <div className="card-header text-center">
          <h1>Chat with AI</h1>
        </div>
        <div className="card-body">
          <div className="chat-box mb-3">
            {messages.map((message, index) => (
              <div key={index} className={`chat-message ${message.sender}`}>
                <strong>{message.sender === 'user' ? 'You' : 'AI'}:</strong> {message.text}
              </div>
            ))}
          </div>
          <form onSubmit={handleSubmit}>
            <div className="input-group">
              <input
                type="text"
                className="form-control"
                placeholder="Enter your prompt"
                value={prompt}
                onChange={(e) => setPrompt(e.target.value)}
                required
                aria-label="Enter your prompt"
              />
              <button type="submit" className="btn btn-info" aria-label="Send message">Send</button>
              <button type="button" className="btn btn-secondary" onClick={startRecognition} aria-label="Start voice recognition"><i className="fas fa-microphone"></i></button>
            </div>
          </form>
        </div>
      </div>
      <style>{`
        .chat-box {
          border: 1px solid #ddd;
          border-radius: 5px;
          padding: 10px;
          background-color: #f9f9f9;
          max-height: 300px;
          overflow-y: auto;
        }

        .chat-message {
          margin-bottom: 10px;
          padding: 10px;
          border-radius: 5px;
          background-color: #e9ecef;
        }

        .chat-message.user {
          background-color: #d1ecf1;
        }

        .chat-message.ai {
          background-color: #e9ecef;
        }

        .input-group .btn {
          margin-left: 5px;
        }

        .input-group .btn:focus {
          outline: 2px solid #0056b3;
          outline-offset: 2px;
        }
      `}</style>
    </div>
  );
};

export default AIChatBot;
