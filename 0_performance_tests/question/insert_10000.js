import http from 'k6/http';
import { check, sleep } from 'k6';

const BASE_URL = 'http://localhost:8080/v1/api/rag';

export let options = {
  vus: 1,
  duration: '1m',
};

export default function () {
  for (let i = 1; i <= 10000; i++) {
    const payload = JSON.stringify({
      title: `Title for question ${i}`,
      content: `Content for question ${i}`,
      category: `Category ${i}`,
      userId: `user_${i}`,
    });

    const params = {
      headers: {
        'Content-Type': 'application/json',
      },
    };

    const url = `${BASE_URL}/questions`;
    const response = http.post(url, payload, params);

    check(response, {
      'status is 201': (r) => r.status === 201,
    });

    sleep(0.1);
  }
}
