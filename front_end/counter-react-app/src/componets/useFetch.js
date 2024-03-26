import {useEffect, useState} from "react";

const useFetch = (url) => {


  const [data, setData] = useState(null);
  const [isLoading, setLoading] = useState(true);
  const [error, setError] = useState(null);


  useEffect(() => {

    const abortCon = new AbortController();


    setTimeout(() => {
      fetch(url, {signal: abortCon.signal})
        .then(res => {
          if (!res.ok) {
            throw Error("Could not fetch the Data!!")
          }
          return res.json();
        })
        .then(data => {
          setData(data);
          setLoading(false)
          setError(null);
        })
        .catch(err => {
          if (err.name === 'AbortError') {
            console.log("Fetch Aborted")
          } else {
            setError(err.message);
            setLoading(false);
          }
        });
    }, 1000);

    return () => abortCon.abort();
  }, [url]);
  return {data, isLoading, error}
}
export default useFetch;
