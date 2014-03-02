package be.vdab.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
class HeartBeatServiceImpl implements HeartBeatService {
	private final RestTemplate restTemplate = new RestTemplate();

	@Override
	@Scheduled(fixedRate = 60000)
	public void sendHeartBeat() {
		restTemplate.getForObject("http://personeel.pepae.cloudbees.net", String.class);
		restTemplate.getForObject("http://bierhuis.pepae.cloudbees.net", String.class);
	}
}
