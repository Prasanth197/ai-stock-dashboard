import React, { useEffect, useState } from "react";
import {
  LineChart, Line, XAxis, YAxis, Tooltip, ResponsiveContainer
} from "recharts";

function App() {
  const [stocks, setStocks] = useState([]);
  const [history, setHistory] = useState([]);
  const [analysis, setAnalysis] = useState("");
  const [selected, setSelected] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/api/stocks/top10")
      .then(res => res.json())
      .then(data => setStocks(data));
  }, []);

  const showGraph = (ticker) => {
    fetch(`http://localhost:8080/api/stocks/${ticker}/history`)
      .then(res => res.json())
      .then(data => {
        setHistory(
          data.history.map((p, i) => ({ day: i + 1, price: p }))
        );
        setSelected(ticker);
      });
  };

  const analyzeStock = (ticker) => {
    setAnalysis(
      "Strong momentum detected with healthy trend stability. Risk is moderate but long-term outlook remains bullish."
    );
  };

  return (
    <div style={styles.page}>
      <h1 style={styles.title}>📊 AI Stock Intelligence</h1>

      <div style={styles.grid}>
        {stocks.map(stock => (
          <div key={stock.ticker} style={styles.card}>
            <h3>{stock.company}</h3>
            <p><b>${stock.price}</b></p>
            <p style={{color: stock.change >= 0 ? "green" : "red"}}>
              {stock.change}%
            </p>

            <div style={styles.btnRow}>
              <button style={styles.btn} onClick={() => showGraph(stock.ticker)}>
                Chart
              </button>
              <button style={styles.aiBtn} onClick={() => analyzeStock(stock.ticker)}>
                AI Insight
              </button>
            </div>
          </div>
        ))}
      </div>

      {history.length > 0 && (
        <div style={styles.chartBox}>
          <h2>{selected} Trend</h2>
          <ResponsiveContainer width="100%" height={300}>
            <LineChart data={history}>
              <XAxis dataKey="day" />
              <YAxis />
              <Tooltip />
              <Line dataKey="price" stroke="#4f46e5" strokeWidth={3} />
            </LineChart>
          </ResponsiveContainer>
        </div>
      )}

      {analysis && (
        <div style={styles.aiBox}>
          <h2>🤖 AI Analysis</h2>
          <p>{analysis}</p>
        </div>
      )}
    </div>
  );
}

const styles = {
  page: {
    padding: 30,
    background: "linear-gradient(120deg,#eef2ff,#f8fafc)",
    minHeight: "100vh",
    fontFamily: "Segoe UI"
  },
  title: {
    textAlign: "center",
    marginBottom: 30,
    color: "#1e293b"
  },
  grid: {
    display: "grid",
    gridTemplateColumns: "repeat(auto-fill,minmax(230px,1fr))",
    gap: 20
  },
  card: {
    background: "white",
    padding: 20,
    borderRadius: 15,
    boxShadow: "0 10px 20px rgba(0,0,0,0.08)",
    transition: "0.3s"
  },
  btnRow: {
    display: "flex",
    justifyContent: "space-between",
    marginTop: 10
  },
  btn: {
    padding: "6px 12px",
    borderRadius: 8,
    border: "none",
    background: "#6366f1",
    color: "white",
    cursor: "pointer"
  },
  aiBtn: {
    padding: "6px 12px",
    borderRadius: 8,
    border: "none",
    background: "#10b981",
    color: "white",
    cursor: "pointer"
  },
  chartBox: {
    background: "white",
    marginTop: 40,
    padding: 20,
    borderRadius: 15,
    boxShadow: "0 10px 20px rgba(0,0,0,0.08)"
  },
  aiBox: {
    marginTop: 20,
    padding: 20,
    borderRadius: 15,
    background: "#ecfeff",
    boxShadow: "0 6px 12px rgba(0,0,0,0.06)"
  }
};

export default App;
