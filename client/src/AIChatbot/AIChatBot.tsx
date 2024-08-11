import React, { useState, FormEvent } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

interface Message {
  sender: 'user' | 'ai';
  text: string;
}

const AIChatBot: React.FC = () => {
  const [prompt, setPrompt] = useState<string>('');
  const [messages, setMessages] = useState<Message[]>([]);

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
    } catch (error) {
      console.error('Error fetching AI response:', error);
    }
    setPrompt('');
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
              />
              <button type="submit" className="btn btn-info">Send</button>
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
      `}</style>
    </div>
  );
};

export default AIChatBot;
